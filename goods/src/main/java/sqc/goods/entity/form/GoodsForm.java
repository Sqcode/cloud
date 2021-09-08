package sqc.goods.entity.form;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import sqc.entity.form.BaseForm;
import sqc.goods.entity.po.Goods;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel
public class GoodsForm extends BaseForm<Goods> {

    @NotBlank(message = "商品名称不能为空")
    @ApiModelProperty("商品名称")
    private String goodName;

    private String description;

    private Integer stock;

    @NotNull(message = "价格不能为空或为0")
    @DecimalMin(value = "0")
    @ApiModelProperty("商品价格")
    private BigDecimal price;

}