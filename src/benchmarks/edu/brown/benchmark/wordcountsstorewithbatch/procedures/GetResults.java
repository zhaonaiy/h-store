package edu.brown.benchmark.wordcountsstorewithbatch.procedures;

import java.util.List;

import org.voltdb.ProcInfo;
import org.voltdb.SQLStmt;
import org.voltdb.VoltProcedure;
import org.voltdb.VoltTable;

import edu.brown.hstore.conf.HStoreConf;
import edu.brown.stream.Batch;
import edu.brown.stream.Tuple;

@ProcInfo (
        singlePartition = true
    )
public class GetResults extends VoltProcedure {
    
    public final SQLStmt selectResultsStmt = new SQLStmt(
            "SELECT word, sum(num) FROM W_RESULTS group by word;"
    		//"INSERT INTO W_WORDS VALUES (?, ?);"
        );
    
    //public long run(String word, int time) 
    public long run()
    {
       voltQueueSQL(selectResultsStmt);
       voltExecuteSQL(true);

       return 0;
    }
}
