package com.losymear.concurrency;

import java.util.concurrent.Phaser;

/**
 * @program: concurrency
 * @description:
 * @author: losymear
 * @create: 2019-03-09 00:27
 */
public class PhaserRuleTest {
    public static void main(String[] args) {
        Phaser ph = new Phaser(1);
        测试Phaser状态(ph); // phaser处于阶段0 已经arrive0  已经register1
        ph.arrive();
        测试Phaser状态(ph); // phaser处于阶段1 已经arrive0  已经register1
        ph.arrive();
        测试Phaser状态(ph); // phaser处于阶段2 已经arrive0  已经register1
        ph.register();
        ph.register();
        测试Phaser状态(ph); // phaser处于阶段2 已经arrive0  已经register3
        ph.arrive();
        测试Phaser状态(ph); // phaser处于阶段2 已经arrive1  已经register3
        ph.arriveAndDeregister();
        测试Phaser状态(ph); // phaser处于阶段2 已经arrive1  已经register2
        System.out.println("a");

        ph.arrive();
        测试Phaser状态(ph); // phaser处于阶段3 已经arrive0  已经register2
        ph.arrive();  // // phaser处于阶段3 已经arrive1  已经register2
        ph.arriveAndDeregister();
        测试Phaser状态(ph); // phaser处于阶段4 已经arrive0  已经register1
        ph.register();
        ph.arriveAndDeregister();
        测试Phaser状态(ph); // phaser处于阶段4 已经arrive0  已经register1
        ph.register();
        ph.register();
        ph.register();
        测试Phaser状态(ph); // phaser处于阶段4 已经arrive0  已经register4
        ph.arriveAndDeregister();
        测试Phaser状态(ph); // phaser处于阶段4 已经arrive0  已经register3
        ph.arriveAndDeregister();
        测试Phaser状态(ph); // phaser处于阶段4 已经arrive0  已经register2

    }

    public static void 测试Phaser状态(Phaser ph) {
        System.out.println("phaser处于阶段" + ph.getPhase() +
                " 已经arrive" + ph.getArrivedParties() +
                "  已经register" + ph.getRegisteredParties());

    }

}
