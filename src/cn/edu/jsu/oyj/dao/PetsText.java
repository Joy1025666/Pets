package cn.edu.jsu.oyj.dao;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.List;

import javax.swing.JOptionPane;

import cn.edu.jsu.oyj.vo.Pets;

public class PetsText {
	public void exportText(List<Pets> list) {
		try (FileWriter fw = new FileWriter(new File("OutputData\\b.txt"));
				BufferedWriter bw = new BufferedWriter(fw)) {
			bw.write("������\t"+"����Ʒ��\t"+"�����Ա�\t"+"��������\t"+"��������\t"+"�����ȶ�\t"+"����۸�\t"+"�������\t");
			bw.newLine();
			for (Pets p : list) {
				bw.write(p.toString());
				bw.newLine();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
