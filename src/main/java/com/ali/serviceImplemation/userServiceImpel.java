package com.ali.serviceImplemation;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.ali.config.appConstants;
import com.ali.entity.Roles;
import com.ali.entity.Users;
import com.ali.exception.resourceException;
import com.ali.payload.UserDTO;
import com.ali.repository.userRepository;
import com.ali.repository.rolesRepo;

import com.ali.services.userService;
@Service
public class userServiceImpel implements userService {
	@Autowired
	private userRepository repo;
	@Autowired
	private ModelMapper modelMapper;
	@Autowired
	private PasswordEncoder posswordEncoder;
	@Autowired
	private rolesRepo rolesRepo;
	@Override
	public UserDTO userCreate(UserDTO user) {
		Users use = this.DtotoUser(user);
		Users savedUser = this.repo.save(use);

		return this.usertodto(savedUser);
	}

	@Override
	public UserDTO updateUser(UserDTO user, int id) {
		// TODO Auto-generated method stub
		Users updatedUser = this.repo.findById(id).orElseThrow(() -> new resourceException("user", "id", id));
		updatedUser.setName(user.getName());
		updatedUser.setEmail(user.getEmail());
		updatedUser.setAbout(user.getAbout());
		Users use = this.repo.save(updatedUser);

		return this.usertodto(use);
	}

	@Override
	public void deleteUser(int id) {
		this.repo.findById(id).orElseThrow(() -> new resourceException("user", "id", id));

		this.repo.deleteById(id);

	}

	@Override
	public UserDTO getUserByID(int id) {
		Users updatedUser = this.repo.findById(id).orElseThrow(() -> new resourceException("user", "id", id));

		return this.usertodto(updatedUser);
	}

	@Override
	public List<UserDTO> showlist() {
		List<Users> list = this.repo.findAll();
		List<UserDTO> userDto = list.stream().map(user -> usertodto(user)).collect(Collectors.toList());
		return userDto;
	}

	public UserDTO usertodto(Users user) {
		UserDTO use =this.modelMapper.map(user, UserDTO.class);
				return use;
	}

	public Users DtotoUser(UserDTO user) {
		Users use = this.modelMapper.map(user, Users.class);
		return use;
	}

	@Override
	public UserDTO registerNewUser(UserDTO userDTO) {
		
		Users user = this.modelMapper.map(userDTO, Users.class);
		user.setPassword(this.posswordEncoder.encode(user.getPassword()));
		Roles role  = this.rolesRepo.findById(appConstants.Normal_User).get();
		
		
		user.getRoles().add(role);
		Users savedUser = this.repo.save(user);
		// TODO Auto-generated method stub
		return this.modelMapper.map(savedUser, UserDTO.class);
	}

}
