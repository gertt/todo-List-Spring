package com.todolist.service.imp;

import com.todolist.entity.ItemEntity;
import com.todolist.exeption.ItemNoFoundExeption;
import com.todolist.model.ItemResponse;
import com.todolist.repository.TodoRepository;
import com.todolist.service.ToDoService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

import static com.todolist.util.AppConstants.SUCCESS;
import static com.todolist.util.DateUtil.stringToDate;

@Service
public class ToDoServiceImp implements ToDoService {

    private final TodoRepository todoRepository;
    private final CloudinaryServiceImp cloudinaryServiceImp;

    public ToDoServiceImp(TodoRepository todoRepository, CloudinaryServiceImp cloudinaryServiceImp) {
        super();
        this.todoRepository = todoRepository;
        this.cloudinaryServiceImp = cloudinaryServiceImp;
    }

    @Override
    public ItemResponse showItem() {
        return new ItemResponse(true, SUCCESS, null, todoRepository.getAllByDate());
    }

    @Override
    public ItemResponse showItemById(Long id) {
        List<ItemEntity> itemEntityList = new ArrayList<>();
        ItemEntity itemEntities = todoRepository.findById(id).orElseThrow(() -> new ItemNoFoundExeption(id));
        itemEntityList.add(itemEntities);

        return new ItemResponse(true, SUCCESS, null, itemEntityList);
    }

    @Override
    @Transactional
    public ItemResponse saveOrUpdateItem(Long id, String tittle, Double latitude, Double longitude, String date, MultipartFile file) {
        ItemEntity itemEntity = new ItemEntity();
        itemEntity.setId(id);
        itemEntity.setTittle(tittle);
        itemEntity.setLatitude(latitude);
        itemEntity.setLongitude(longitude);
        itemEntity.setDate(stringToDate(date));
        itemEntity.setUrlImage(cloudinaryServiceImp.uploadFile(file));

        List<ItemEntity> list = new ArrayList<>();
        list.add(todoRepository.save(itemEntity));
        return new ItemResponse(true, SUCCESS, null, list);
    }

    @Override
    @Transactional
    public ItemResponse deleteItem(Long id) throws ItemNoFoundExeption {
        ItemEntity itemEntity = new ItemEntity();
        itemEntity.setId(id);
        if (todoRepository.findById(id).isEmpty())
            throw new ItemNoFoundExeption(id);
        else
            todoRepository.delete(itemEntity);
        return new ItemResponse(true, SUCCESS, null, null);
    }
}
