package cn.edu.jsu.oyj.dao;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class DataOperateTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void test() {
		DataOperate.getSelectAll("select *from pets");
	}

}
