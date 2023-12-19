package com.inseoul.board.domain.post;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class QryCommentList extends QryResult{

    @ToString.Exclude
    @JsonProperty("data")   // JSON 변환시 "data" 란 이름의 property 로 변환됨.
    List<Comment> list;     // 댓글 데이터가 저장 되어있는 배열
}