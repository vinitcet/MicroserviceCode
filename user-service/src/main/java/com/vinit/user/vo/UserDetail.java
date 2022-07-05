package com.vinit.user.vo;

import com.vinit.user.entity.Users;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDetail {
    private Users user;
    private Department department;

}
