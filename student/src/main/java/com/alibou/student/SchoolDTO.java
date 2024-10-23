package com.alibou.student;

import  lombok.*;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SchoolDTO {

    private Integer id;
    private String name;
    private String email;
}
