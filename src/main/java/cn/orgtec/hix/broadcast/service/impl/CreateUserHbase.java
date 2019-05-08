package cn.orgtec.hix.broadcast.service.impl;

import cn.orgtec.hix.broadcast.controller.HBaseUtil;
import cn.orgtec.hix.broadcast.entity.HBaseConnection;
import cn.orgtec.hix.broadcast.entity.UserRepository;
import org.apache.hadoop.hbase.HColumnDescriptor;
import org.apache.hadoop.hbase.HTableDescriptor;
import org.apache.hadoop.hbase.client.HBaseAdmin;
import org.apache.hadoop.hbase.util.Bytes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.hadoop.hbase.HbaseTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.IOException;

/**
 * @author Yibo Zhang
 * @date 2019/05/08
 */
@Service
public class CreateUserHbase {

    private String tableName = "users";
	private byte[] tableNameAsBytes = Bytes.toBytes("users");


    @Autowired
    private HbaseTemplate hbaseTemplate;

    @Autowired
    private UserRepository userRepository;

    HBaseAdmin admin;

    {
        try {
            admin = (HBaseAdmin) HBaseConnection.getHBaseConnection().getAdmin();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void initialize() throws IOException {
        boolean sys_bro = HBaseUtil.createTable("sys_bro", new String[]{"bro_info"});
        System.err.println(sys_bro);
//        if (admin.tableExists(tableNameAsBytes)) {
//            if (!admin.isTableDisabled(tableNameAsBytes)) {
//                System.out.printf("Disabling %s\n", tableName);
//                admin.disableTable(tableNameAsBytes);
//            }
//            System.out.printf("Deleting %s\n", tableName);
//            admin.deleteTable(tableNameAsBytes);
//        }
//
//        HTableDescriptor tableDescriptor = new HTableDescriptor(tableName);
//        HColumnDescriptor columnDescriptor = new HColumnDescriptor(
//                UserRepository.CF_INFO);
//        tableDescriptor.addFamily(columnDescriptor);
//
//        admin.createTable(tableDescriptor);
//        System.err.println("初始化用户");
    }

    public void addUsers() {
        for (int i = 0; i < 10; i++) {
            userRepository.save("user" + i,"user" + i + "@yahoo.com", "password" + i);
        }
    }
}
