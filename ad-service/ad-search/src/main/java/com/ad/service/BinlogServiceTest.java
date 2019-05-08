package com.ad.service;

import com.github.shyiko.mysql.binlog.BinaryLogClient;
import com.github.shyiko.mysql.binlog.event.DeleteRowsEventData;
import com.github.shyiko.mysql.binlog.event.EventData;
import com.github.shyiko.mysql.binlog.event.UpdateRowsEventData;
import com.github.shyiko.mysql.binlog.event.WriteRowsEventData;

import java.io.IOException;

/**
 * created by Mr.F on 2019/5/8
 */
//测试类，看是否能监听Binlog
public class BinlogServiceTest {
    public static void main(String[] args) throws IOException {
        BinaryLogClient client =new BinaryLogClient(
                "127.0.0.1",
                3306,
                "root",
                "282818"

        );
        client.registerEventListener(event -> {
            EventData data=event.getData();
            if(data instanceof UpdateRowsEventData){
                System.out.println("begin update");
                System.out.println(data.toString());
            }else if (data instanceof WriteRowsEventData){
                System.out.println("begin write");
                System.out.println(data.toString());
            }else if (data instanceof DeleteRowsEventData){
                System.out.println("begin delete");
                System.out.println(data.toString());
            }
        });
        client.connect();
    }
}
