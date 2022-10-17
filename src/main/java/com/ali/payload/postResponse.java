package com.ali.payload;

import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
public class postResponse {
List<postDTO> content;
int  pageNumber;
int pageSize;
int totalElements;
int totalPage;
Boolean isLastPage;

}
