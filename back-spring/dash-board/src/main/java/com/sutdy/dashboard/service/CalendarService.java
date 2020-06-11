package com.sutdy.dashboard.service;

import com.sutdy.dashboard.domain.calendars.Task;
import com.sutdy.dashboard.domain.calendars.TaskRepository;
import com.sutdy.dashboard.domain.calendars.TaskTag;
import com.sutdy.dashboard.domain.calendars.TaskTagRepository;
import com.sutdy.dashboard.domain.members.Account;
import com.sutdy.dashboard.domain.members.AccountRepository;
import com.sutdy.dashboard.dto.AccountDto;
import com.sutdy.dashboard.dto.TaskDto;
import com.sutdy.dashboard.dto.TaskTagDto;
import com.sutdy.dashboard.service.common.BaseCrudService;
import com.sutdy.dashboard.setting.common.SearchParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * @author kuh
 * @since 2020.06.11
 */
@Service("calendarService")
public class CalendarService extends BaseCrudService<Task, TaskDto, Long> {

    private TaskTagRepository taskTagRepository;
    private AccountRepository accountRepository;

    @Autowired
    public CalendarService(TaskRepository taskRepository,
                           AccountRepository accountRepository,
                           TaskTagRepository taskTagRepository) {
        super(taskRepository);
        this.taskTagRepository = taskTagRepository;
        this.accountRepository = accountRepository;
    }


    @Override
    public TaskDto save(TaskDto dto) throws NoSuchAlgorithmException {
        return this.entitySave(dto.toEntity());
    }

    @Override
    public TaskDto update(Long pk, TaskDto dto) {
        Task entity = this.entityFindById(pk);
        entity.patch(dto);
        return new TaskDto(entity);
    }

    @Override
    public TaskDto delete(Long pk) {
        return this.entityDelete(pk);
    }

    @Override
    public Page<TaskDto> findAll(int page, int size) {
        return this.entityFindAll(page, size);
    }

    @Override
    public List<TaskDto> findAll() {
        return this.entityFindAll();
    }

    @Override
    public List<TaskDto> findAllById(Iterable<Long> ids) {
        return null;
    }

    @Override
    public List<TaskDto> findAll(SearchParams params) {
        return null;
    }

    @Override
    public TaskDto findById(Long pk) {
        return null;
    }


    @Transactional
    public TaskTagDto tagUpdate(TaskTagDto dto) {
        TaskTag origin = this.taskTagRepository.findById(dto.getId())
                .orElseThrow(() -> new IllegalArgumentException(NOT_FIND_DATA));

        origin.patch(dto);
        return new TaskTagDto(origin);
    }

    public List<TaskTagDto> tagFindAllByPager(Pageable pageable) {
        List<TaskTag> datas = this.taskTagRepository.findAll(pageable).toList();
        List<TaskTagDto> result = new ArrayList<>();
        datas.forEach(t -> {
            if (t != null) {
                result.add(new TaskTagDto(t));
            }
        });
        return result;
    }

    public List<TaskTagDto> tagFindAllById(Iterable<Long> ids) {
        List<TaskTag> datas = this.taskTagRepository.findAllById(ids);
        List<TaskTagDto> result = new ArrayList<>();
        datas.forEach(t -> {
            if (t != null) {
                result.add(new TaskTagDto(t));
            }
        });
        return result;
    }

    @Transactional
    public TaskTagDto tagDelete(Long pk) {
        TaskTagDto dto = this.tagFindById(pk);
        this.taskTagRepository.deleteById(pk);
        return dto;
    }


    @Transactional
    public TaskTagDto tagSave(TaskTagDto dto) {
        TaskTag entity = dto.toEntity();

        if(dto.getUserId() != null){
            Account account = this.accountRepository.findById(dto.getUserId())
                    .orElseThrow(()-> new IllegalArgumentException(NOT_FIND_DATA));

            entity.setAccount(account);
        }
        return new TaskTagDto(this.taskTagRepository.save(entity));
    }

    public TaskTagDto tagFindById(Long id) {
        TaskTag task = this.taskTagRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException(NOT_FIND_DATA));
        return new TaskTagDto(task);
    }
}
