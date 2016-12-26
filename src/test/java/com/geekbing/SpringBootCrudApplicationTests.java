package com.geekbing;

import com.geekbing.entity.Department;
import com.geekbing.entity.Role;
import com.geekbing.entity.User;
import com.geekbing.repository.DepartmentRepository;
import com.geekbing.repository.RoleRepository;
import com.geekbing.repository.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.Assert;

import java.util.Date;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringBootCrudApplicationTests {

	private static Logger logger = LoggerFactory.getLogger(SpringBootCrudApplicationTests.class);

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private RoleRepository roleRepository;
	@Autowired
	private DepartmentRepository departmentRepository;

	@Before
	public void initData() {
		userRepository.deleteAll();
		roleRepository.deleteAll();
		departmentRepository.deleteAll();

		Department department = new Department();
		department.setName("开发部");
		departmentRepository.save(department);
		Assert.notNull(department.getId());

		Role role = new Role();
		role.setName("admin");
		roleRepository.save(role);
		Assert.notNull(role.getId());

		User user = new User();
		user.setName("user");
		user.setCreatedate(new Date());
		user.setDepartment(department);
		List<Role> roles = roleRepository.findAll();
		Assert.notNull(roles);

		user.setRoles(roles);
		userRepository.save(user);
		Assert.notNull(user.getId());
	}

	@Test
	public void contextLoads() {
		Pageable pageable = new PageRequest(0, 10, new Sort(Sort.Direction.ASC, "id"));
		Page<User> page = userRepository.findAll(pageable);
		Assert.notNull(page);
		for (User user : page.getContent()) {
			logger.info("====user==== user name:{}, department name:{}, rolename:{}", user.getName(), user.getDepartment().getName(), user.getRoles().get(0).getName());
		}

	}

}
