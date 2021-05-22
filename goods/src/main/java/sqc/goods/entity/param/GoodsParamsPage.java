package sqc.goods.entity.param;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import sqc.goods.entity.po.Goods;

@Data
@ApiModel
public class GoodsParamsPage extends BaseParamPage<Goods> {

    @ApiModelProperty("id")
    private String id;

    @ApiModelProperty("商品名称")
    private String goodName;

//    @ApiModelProperty(value = "时间date：年月日，2021-05-12")
//    @Pattern(regexp = "((((19|20)\\d{2})-(0?(1|[3-9])|1[012])-(0?[1-9]|[12]\\d|30))|(((19|20)\\d{2})-(0?[13578]|1[02])-31)|(((19|20)\\d{2})-0?2-(0?[1-9]|1\\d|2[0-8]))|((((19|20)([13579][26]|[2468][048]|0[48]))|(2000))-0?2-29))$", message = "日期格式不正确，yyyy-MM-dd")
//    private String date;

}
