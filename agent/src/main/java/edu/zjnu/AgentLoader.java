package edu.zjnu;

import com.sun.tools.attach.*;

import java.io.IOException;
import java.util.List;

/**
 * @description: AgentLoader
 * @author: 杨海波
 * @date: 2022-06-23 09:36
 **/
public class AgentLoader {

    public static void main(String[] args) throws IOException, AttachNotSupportedException, AgentLoadException, AgentInitializationException {
        List<VirtualMachineDescriptor> vms = VirtualMachine.list();
        for (VirtualMachineDescriptor vm : vms) {
            if ("edu.zjnu.TargetVM".equals(vm.displayName())) {
                VirtualMachine machine = VirtualMachine.attach(vm.id());
                machine.loadAgent("/Users/SetsunaYang/Documents/learning/learning/agent/src/main/resources/agent-0.0.1-SNAPSHOT.jar");
                System.out.println(vm.displayName());
            }
        }
    }
}
