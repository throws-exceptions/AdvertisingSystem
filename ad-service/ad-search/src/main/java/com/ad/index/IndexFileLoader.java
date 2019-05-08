package com.ad.index;

import com.alibaba.fastjson.JSON;
import com.ad.dump.DConstant;
import com.ad.dump.table.AdCreativeTable;
import com.ad.dump.table.AdCreativeUnitTable;
import com.ad.dump.table.AdPlanTable;
import com.ad.dump.table.AdUnitDistrictTable;
import com.ad.dump.table.AdUnitItTable;
import com.ad.dump.table.AdUnitKeywordTable;
import com.ad.dump.table.AdUnitTable;
import com.ad.handler.AdLevelDataHandler;
import com.ad.mysql.constant.OpType;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Mr.F on 2019/5/4.
 */
@Component
@DependsOn("dataTable")//会依赖DataTable
public class IndexFileLoader {

    /**
     * 被@PostConstruct修饰的方法会在服务器加载Servlet的时候运行
     * 并且只会被服务器执行一次。
     * 服务器加载Servlet时会有以下流程：
     * Servlet构造函数 -> PostConstruct注解修饰的方法 -> Init -> Service -> destroy -> PreDestroy ->服务器卸载Servlet
     * 这里是想让服务器刚初始化时就会完成全量数据的加载,并且加载的时候一定按照层级依赖的关系进行加载
     */
    @PostConstruct
    public void init() {
        //对AdPlan表的加载
        List<String> adPlanStrings = loadDumpData(
                String.format("%s%s",
                        DConstant.DATA_ROOT_DIR,
                        DConstant.AD_PLAN)
        );
        //从数据库读取出来的是JSON格式的String，这里需要遍历的将其反序列化成AdPlanTable
        adPlanStrings.forEach(p -> AdLevelDataHandler.handleLevel2(
                JSON.parseObject(p, AdPlanTable.class),
                OpType.ADD
        ));

        List<String> adCreativeStrings = loadDumpData(
                String.format("%s%s",
                        DConstant.DATA_ROOT_DIR,
                        DConstant.AD_CREATIVE)
        );
        adCreativeStrings.forEach(c -> AdLevelDataHandler.handleLevel2(
                JSON.parseObject(c, AdCreativeTable.class),
                OpType.ADD
        ));

        List<String> adUnitStrings = loadDumpData(
                String.format("%s%s",
                        DConstant.DATA_ROOT_DIR,
                        DConstant.AD_UNIT)
        );
        adUnitStrings.forEach(u -> AdLevelDataHandler.handleLevel3(
                JSON.parseObject(u, AdUnitTable.class),
                OpType.ADD
        ));

        List<String> adCreativeUnitStrings = loadDumpData(
                String.format("%s%s",
                        DConstant.DATA_ROOT_DIR,
                        DConstant.AD_CREATIVE_UNIT)
        );
        adCreativeUnitStrings.forEach(cu -> AdLevelDataHandler.handleLevel3(
                JSON.parseObject(cu, AdCreativeUnitTable.class),
                OpType.ADD
        ));

        List<String> adUnitDistrictStrings = loadDumpData(
                String.format("%s%s",
                        DConstant.DATA_ROOT_DIR,
                        DConstant.AD_UNIT_DISTRICT)
        );
        adUnitDistrictStrings.forEach(d -> AdLevelDataHandler.handleLevel4(
                JSON.parseObject(d, AdUnitDistrictTable.class),
                OpType.ADD
        ));

        List<String> adUnitItStrings = loadDumpData(
                String.format("%s%s",
                        DConstant.DATA_ROOT_DIR,
                        DConstant.AD_UNIT_IT)
        );
        adUnitItStrings.forEach(i -> AdLevelDataHandler.handleLevel4(
                JSON.parseObject(i, AdUnitItTable.class),
                OpType.ADD
        ));

        List<String> adUnitKeywordStrings = loadDumpData(
                String.format("%s%s",
                        DConstant.DATA_ROOT_DIR,
                        DConstant.AD_UNIT_KEYWORD)
        );
        adUnitKeywordStrings.forEach(k -> AdLevelDataHandler.handleLevel4(
                JSON.parseObject(k, AdUnitKeywordTable.class),
                OpType.ADD
        ));
    }
    //实现读取文件并将其转换成List<String>
    private List<String> loadDumpData(String fileName) {

        try (BufferedReader br = Files.newBufferedReader(
                Paths.get(fileName)//获取当前文件的位置
        )) {
            return br.lines().collect(Collectors.toList());//该方法能实现将流中的String收集成List
        } catch (IOException ex) {
            throw new RuntimeException(ex.getMessage());
        }
    }
}
