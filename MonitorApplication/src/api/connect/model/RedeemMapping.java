/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package api.connect.model;

import core.redeem.model.RedeemModel;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 *
 * @author nateesun
 */
@Getter
@Setter
@ToString
public class RedeemMapping {
    private String database;
    private String action_status;
    private RedeemModel []data;
}
