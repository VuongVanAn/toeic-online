package vn.myclass.core.utils;

import vn.myclass.core.dto.ExerciseQuestionDTO;
import vn.myclass.core.persistence.entity.ExerciseQuestionEntity;

public class ExerciseQuestionBeanUtil {
    public static ExerciseQuestionDTO toDto(ExerciseQuestionEntity entity) {
        ExerciseQuestionDTO dto = new ExerciseQuestionDTO();
        dto.setExerciseQuestionId(entity.getExerciseQuestionId());
        dto.setAudio(entity.getAudio());
        dto.setImage(entity.getImage());
        dto.setCorrectAnswer(entity.getCorrectAnswer());
        dto.setQuestion(entity.getQuestion());
        dto.setOption1(entity.getOption1());
        dto.setOption2(entity.getOption2());
        dto.setOption3(entity.getOption3());
        dto.setOption4(entity.getOption4());
        dto.setCreatedDate(entity.getCreatedDate());
        dto.setModifiedDate(entity.getModifiedDate());
        return dto;
    }
    public static ExerciseQuestionEntity toEntity(ExerciseQuestionDTO dto) {
        ExerciseQuestionEntity entity = new ExerciseQuestionEntity();
        entity.setExerciseQuestionId(dto.getExerciseQuestionId());
        entity.setAudio(dto.getAudio());
        entity.setImage(dto.getImage());
        entity.setCorrectAnswer(dto.getCorrectAnswer());
        entity.setQuestion(dto.getQuestion());
        entity.setOption1(dto.getOption1());
        entity.setOption2(dto.getOption2());
        entity.setOption3(dto.getOption3());
        entity.setOption4(dto.getOption4());
        entity.setCreatedDate(dto.getCreatedDate());
        entity.setModifiedDate(dto.getModifiedDate());
        return entity;
    }
}
