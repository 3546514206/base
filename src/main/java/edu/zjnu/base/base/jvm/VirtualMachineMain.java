package edu.zjnu.base.base.jvm;

import com.sun.tools.attach.AttachNotSupportedException;
import com.sun.tools.attach.VirtualMachine;
import com.sun.tools.attach.VirtualMachineDescriptor;

import java.io.IOException;
import java.util.List;

/**
 * @description: VirtualMachineMain
 * @author: 杨海波
 * @date: 2022-06-07 11:04
 **/
public class VirtualMachineMain {

    public static void main(String[] args) throws IOException, AttachNotSupportedException {
//        VirtualMachine vm = VirtualMachine.attach("2426");
        List<VirtualMachineDescriptor> virtualMachineDescriptorList = VirtualMachine.list();
        System.out.println("");

    }
}
