package sqc.goods.entity.vo;

import lombok.Data;
import sqc.entity.po.BasePO;

import java.math.BigDecimal;

@Data
public class GoodsVO extends BasePO {

    private String goodName;

//    private String description;

    private Integer stock;

    private BigDecimal price;

}
