# 并发异常
https://www.cnblogs.com/snowater/p/8024776.html
https://blog.csdn.net/kingzone_2008/article/details/41368989

```java
import com.ehi.csync.enums.UpdateWorkflowIdEnum;
import com.ehi.csync.update.column.domain.UpdateColumn;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.TimeUnit;

/**
 * @author landyl
 * @create 4:17 PM 12/26/2018
 */
public class UpdateColumnHandler {
    private static final Logger LOGGER = LoggerFactory.getLogger(UpdateColumnHandler.class);

    private final Map<UpdateWorkflowIdEnum,List<UpdateColumn>> UPDATE_COLUMNS_MAP = new ConcurrentHashMap<>();

    private static final List<Integer> WORKFLOW_IDS = Arrays.asList(1,4,5,8);

    private static List<UpdateColumn> updateColumnList = new ArrayList<>();
    private static Map<Integer,List<UpdateColumn>> UPDATE_COLUMNS_MAP_TEMP = new ConcurrentHashMap<>();

    static {
        for (int i = 0;i<10;i++) {
            for(int j=0;j<WORKFLOW_IDS.size();j++) {
                UpdateColumn updateColumn = new UpdateColumn();
                updateColumn.setUpdateColumnId(i);
                updateColumn.setUpdateColumnName("Landy" + i);
                int updateWorkId = WORKFLOW_IDS.get(j);
                updateColumn.setUpdateWorkflowId(updateWorkId);
                updateColumnList.add(updateColumn);
                if(UPDATE_COLUMNS_MAP_TEMP.containsKey(updateWorkId)) {
                    UPDATE_COLUMNS_MAP_TEMP.get(updateWorkId).add(updateColumn);
                } else {
                    List<UpdateColumn> updateColumns = new ArrayList<>();
                    updateColumns.add(updateColumn);
                    UPDATE_COLUMNS_MAP_TEMP.put(updateWorkId,updateColumns);
                }
            }
        }
    }

    public void initUpdateColumnsMap() {
        LOGGER.info("Start to init the Update Column data from EHI_CSYNC.CSYNC_UPDATE_COLUMN table.");
        this.clearUpdateColumnsMap();
        List<UpdateColumn> updateColumns = updateColumnList;
        addUpdateColumnsMap(updateColumns);
        LOGGER.info("Ending init the Update Column data from EHI_CSYNC.CSYNC_UPDATE_COLUMN table.");
    }

    public List<UpdateColumn> getUpdateColumns(UpdateWorkflowIdEnum updateWorkflow) {
        if (!UPDATE_COLUMNS_MAP.containsKey(updateWorkflow)) {
            List<UpdateColumn> updateColumns = UPDATE_COLUMNS_MAP_TEMP.get(updateWorkflow.getValue());
            addUpdateColumnsMap(updateColumns);
        }
        List<UpdateColumn> updateColumns = UPDATE_COLUMNS_MAP.get(updateWorkflow);
        if(updateColumns == null) {
            System.out.println("================================================" + updateWorkflow.getWorkflowName() + "++++++++++++++++++" + updateWorkflow.getValue());
        }
        updateColumns.sort(new UpdateColumnComparator());
        return updateColumns;
    }

    public void clearUpdateColumnsMap() {
        UPDATE_COLUMNS_MAP.clear();
    }

    public void clearUpdateColumnsMap(UpdateWorkflowIdEnum updateWorkflow) {
        if(UPDATE_COLUMNS_MAP.containsKey(updateWorkflow)) {
            UPDATE_COLUMNS_MAP.remove(updateWorkflow);
        }
    }

    private void addUpdateColumnsMap(List<UpdateColumn> updateColumns) {
        if(CollectionUtils.isNotEmpty(updateColumns)) {
            updateColumns.stream().forEach(updateColumn -> {
                Integer workflowId = updateColumn.getUpdateWorkflowId();
                UpdateWorkflowIdEnum workflow = UpdateWorkflowIdEnum.fromValue(workflowId);
                if(UpdateWorkflowIdEnum.isCommLog(workflowId)) { //if current column is COM_LOG,convert it to POLICY
                    workflow = UpdateWorkflowIdEnum.UPDATE_WORKFLOW_POLICY_TRACKING;
                }
                this.addUpdateColumnsMap(workflow,updateColumn);
            });
        }
    }

    private void addUpdateColumnsMap(UpdateWorkflowIdEnum updateWorkflow,UpdateColumn updateColumn) {
        LOGGER.info("Add update column [{}] into cache map, workflow is :{} .",updateColumn.getUpdateColumnName(),updateWorkflow.getWorkflowName());
        if(UPDATE_COLUMNS_MAP.containsKey(updateWorkflow)) {
            UPDATE_COLUMNS_MAP.get(updateWorkflow).add(updateColumn);
        } else {
            List<UpdateColumn> updateColumns = new CopyOnWriteArrayList<>();
            updateColumns.add(updateColumn);
            UPDATE_COLUMNS_MAP.put(updateWorkflow,updateColumns);
        }
    }

    private class UpdateColumnComparator implements Comparator<UpdateColumn> {
        @Override
        public int compare(UpdateColumn updateColumn1, UpdateColumn updateColumn2) {
            if (updateColumn1 != null && updateColumn2 != null) {
                return updateColumn1.getUpdateColumnId() - updateColumn2.getUpdateColumnId();
            }
            return 0;
        }
    }

    public static void main(String[] args) {
        UpdateColumnHandler updateColumnHandler = new UpdateColumnHandler();
        updateColumnHandler.initUpdateColumnsMap();
        for(int i=0;i<50;i++) {
            new Thread(()->{
                int index = new Random().nextInt(4);
                int updateWorkId = WORKFLOW_IDS.get(index);
                List<UpdateColumn> updateColumns = updateColumnHandler.getUpdateColumns(UpdateWorkflowIdEnum.fromValue(updateWorkId));

                if(CollectionUtils.isNotEmpty(updateColumns)) {
                    for (UpdateColumn updateColumn : updateColumns) {
                        System.out.println(updateColumn.getUpdateColumnName() + "_" + updateColumn.getUpdateWorkflowId() + "_" + updateColumn.getUpdateColumnId());
                    }
                }

                System.out.println("current thread:" + Thread.currentThread().getName());
                try {
                    TimeUnit.SECONDS.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();
        }
//        for(int i=0;i<50;i++) {
//            int index = new Random().nextInt(4);
//            System.out.println(index);
//        }
    }
}

```
