package ${cfg.packageRootPath}.common.model.vo;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class QueryListVo<T> {
    private Integer total = 0;
    private List<T> rows = new ArrayList();
}
