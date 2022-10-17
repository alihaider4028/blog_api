package com.ali.services;

import java.util.List;


import com.ali.payload.UserDTO;
public interface userService {
  UserDTO registerNewUser(UserDTO user);
  UserDTO userCreate(UserDTO user);
  UserDTO updateUser(UserDTO user, int id);
  void deleteUser( int id);
  UserDTO getUserByID(int id);
  List<UserDTO> showlist();

}
