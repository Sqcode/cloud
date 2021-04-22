package sqc.goods.entity.po;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import sqc.entity.po.BasePO;

import java.math.BigDecimal;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Goods extends BasePO {

    private String goodName;

    private String description;

    private Integer stock;

    private BigDecimal price;

}