package com.sutdy.dashboard.service;

import com.sutdy.dashboard.domain.calendars.Task;
import com.sutdy.dashboard.domain.calendars.TaskRepository;
import com.sutdy.dashboard.domain.calendars.TaskTag;
import com.sutdy.dashboard.domain.calendars.TaskTagRepository;
import com.sutdy.dashboard.domain.members.Account;
import com.sutdy.dashboard.domain.members.AccountRepository;
import com.sutdy.dashboard.dto.TaskDto;
import com.sutdy.dashboard.dto.TaskTagDto;
import com.sutdy.dashboard.service.common.BaseCrudService;
import com.sutdy.dashboard.setting.common.SearchParams;
import com.sutdy.dashboard.setting.util.data.ModelConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
    public TaskDto update(Long pk, TaskDto dto) {
        Task entity = this.findEntityById(pk);
        entity.patch(dto);
        return new TaskDto(entity);
    }

    @Transactional
    public TaskTagDto tagUpdate(TaskTagDto dto) {
        TaskTag origin = this.taskTagRepository.findById(dto.getId())
                .orElseThrow(() -> new IllegalArgumentException(NOT_FIND_DATA));

        origin.patch(dto);
        return new TaskTagDto(origin);
    }

    public List<TaskTagDto> tagFindAllById(Iterable<Long> ids) {
        return this.taskTagRepository.findAllById(ids)
                .stream()
                .map(t-> new TaskTagDto(t))
                .collect(Collectors.toList());
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
        return new TaskTagDto(entity);
    }

    public TaskTagDto tagFindById(Long id) {
        TaskTag task = this.taskTagRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException(NOT_FIND_DATA));
        return new TaskTagDto(task);
    }
}
