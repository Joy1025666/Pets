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
			bw.write("宠物编号\t"+"宠物品种\t"+"宠物性别\t"+"宠物体重\t"+"宠物在售\t"+"宠物热度\t"+"宠物价格\t"+"宠物类别\t");
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
