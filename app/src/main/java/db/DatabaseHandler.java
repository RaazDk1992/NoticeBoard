package db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

public class DatabaseHandler extends SQLiteOpenHelper {


    private static final String dbName = "NoticeBoardCacheTable";
    private static  final  int dbVersion = 1;
    /**
     * Table stores the Service details.
     */
    private static  final String serviceTable = "ServicesTable";

    private static  final String id_field = "SID";
    private static  final String service_name = "SNAME";

    private static  final String req_time = "ReqTime";
    private static  final String res_person = "ResPerson";
    private static  final String res_dept = "Dept";
    /**
     * Table stores the required documents for the service.
     */
    private static  final String RequiredDocuments = "DocsTable";
    private static  final String req_docs = "ReqDocs";

    /**
     * Table stores the process to get the service.
     */
    private static  final String ProcessTable = "ProcessTable";
    private static  final String process = "Process";

    /**
     *
     * Queries
     *
     *
     */
    private static final String CreateTableServices = "CREATE TABLE "+serviceTable+"("
            +id_field+" INTEGER PRIMARY KEY"
            +service_name+" TEXT"
            +req_time+" TEXT"
            +res_person+" TEXT"
            +req_docs +"TEXT"
            +res_dept+" TEXT"
            +")";


    /**
     *
     * @param context
     */

    public DatabaseHandler(Context context) {
        super(context, dbName, null, dbVersion);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
