package ${package.Entity}.param;

import lombok.Data;
import java.util.ArrayList;
import java.util.List;

@Data
public class ${entity}BatchCreateParam {

    private List<${entity}CreateParam> list = new ArrayList<>();
}
