package com.cg.service;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;
import java.util.ArrayList;
import java.util.List;

import com.cg.entities.Test;
import com.cg.repositories.TestRepository;
import com.cg.services.TestServiceImpl;

@ExtendWith(MockitoExtension.class)
@ExtendWith(SpringExtension.class)
@SpringBootTest
public class TestServiceTests {

	@InjectMocks
	private TestServiceImpl testService;

	@MockBean
	private TestRepository testRepo;

	@org.junit.jupiter.api.Test
	public void testAddTests() {
		Test test = new Test();
		test.setTestName("Android");
		test.setTotalMarks(5);
		Mockito.when(testRepo.save(test)).thenReturn(test);
		assertThat(testService.saveTest(test)).isEqualTo(test);
	}

	@org.junit.jupiter.api.Test
	public void testViewAllTests() {
		Test test = new Test();
		test.setTestName("Mathematics");
		test.setTotalMarks(10);

		Test test0 = new Test();
		test.setTestName("History");
		test.setTotalMarks(10);

		List<Test> testList = new ArrayList<>();
		testList.add(test);
		testList.add(test0);

		Mockito.when(testRepo.findAll()).thenReturn(testList);
		assertThat(testService.getAllTests()).isEqualTo(testList);
	}

	@org.junit.jupiter.api.Test
	public void testDeleteTests() {
		Test test = new Test();
		test.setTestId(10);
		test.setTestName("English");
		test.setTotalMarks(10);

		Mockito.when(testRepo.getOne(1)).thenReturn(test);
		Mockito.when(testRepo.existsById(test.getTestId())).thenReturn(false);
		assertFalse(testRepo.existsById(test.getTestId()));
	}
}
