package cn.seeyoui.mp.generator.template.param;

import lombok.Data;

import java.util.List;

@Data
public class Schema {
	private String moduleName;
	private List<Table> tableList;
}
