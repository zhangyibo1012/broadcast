package cn.orgtec.hix.broadcast.dto;

import lombok.Data;

/**
 * <p>Behavior.java此类用于</p>
 * <p>@author zyb <p>
 * <p>@date 2019/04/02 <p>
 * <p>@remark:</p>
 */
@Data
public class Behavior {
    private Comment comment;
    private Favor favor;
    private Hate hate;
    private Reward reward;
}
