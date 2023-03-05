package ${cfg.packageRootPath}.common.model.paging;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "列表分页参数对象")
public class BasePaging {

	private static final long serialVersionUID = 1L;

	@ApiModelProperty(value = "排序字段名", hidden = true)
	private String orderColumn;

	@ApiModelProperty(value = "排序方向：ASC true, DESC false, 默认 null", hidden = true)
	private Boolean orderDirection;

	@ApiModelProperty(value = "排序类型：数字排序 false, 字符排序(中文) true, 默认 null", hidden = true)
	private Boolean orderType;

	@ApiModelProperty(value = "当前页", example = "1")
	private Integer currentPage;

	@ApiModelProperty(value = "当前页起始行数（自动设置）", hidden = true)
	private Integer currentPageOffset;

	@ApiModelProperty(value = "每页展示数据条数", example = "10")
	private Integer pageSize;

	private void calculateOffset() {
		if (null != currentPage && null != pageSize) {
			currentPage = currentPage < 1 ? 1 : currentPage;
			this.currentPageOffset = (currentPage - 1) * pageSize;
		}
	}
	
}
