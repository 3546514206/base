package edu.zjnu.base.jvm;

import com.sun.tools.attach.AttachNotSupportedException;
import com.sun.tools.attach.VirtualMachine;

import java.io.IOException;

/**
 * @description: VirtualMachineMain
 * @author: 杨海波
 * @date: 2022-06-07 11:04
 **/
public class VirtualMachineMain {

    public static void main(String[] args) throws IOException, AttachNotSupportedException {
        VirtualMachine vm = VirtualMachine.attach("2426");
        System.out.println("");
    }
}
