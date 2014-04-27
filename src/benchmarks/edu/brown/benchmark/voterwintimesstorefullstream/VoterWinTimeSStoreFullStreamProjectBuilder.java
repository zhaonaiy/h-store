/***************************************************************************
 *  Copyright (C) 2012 by H-Store Project                                  *
 *  Brown University                                                       *
 *  Massachusetts Institute of Technology                                  *
 *  Yale University                                                        *
 *                                                                         *
 *  Coded By:  Justin A. DeBrabant (http://www.cs.brown.edu/~debrabant/)   *								   
 *                                                                         *
 *                                                                         *
 *  Permission is hereby granted, free of charge, to any person obtaining  *
 *  a copy of this software and associated documentation files (the        *
 *  "Software"), to deal in the Software without restriction, including    *
 *  without limitation the rights to use, copy, modify, merge, publish,    *
 *  distribute, sublicense, and/or sell copies of the Software, and to     *
 *  permit persons to whom the Software is furnished to do so, subject to  *
 *  the following conditions:                                              *
 *                                                                         *
 *  The above copyright notice and this permission notice shall be         *
 *  included in all copies or substantial portions of the Software.        *
 *                                                                         *
 *  THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,        *
 *  EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF     *
 *  MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. *
 *  IN NO EVENT SHALL THE AUTHORS BE LIABLE FOR ANY CLAIM, DAMAGES OR      *
 *  OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE,  *
 *  ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR  *
 *  OTHER DEALINGS IN THE SOFTWARE.                                        *
 ***************************************************************************/

package edu.brown.benchmark.voterwintimesstorefullstream;

import org.voltdb.VoltProcedure;

import edu.brown.benchmark.AbstractProjectBuilder;
import edu.brown.api.BenchmarkComponent;

import edu.brown.benchmark.voterwintimesstorefullstream.procedures.ValidateContestantsTrigger;
import edu.brown.benchmark.voterwintimesstorefullstream.procedures.UpdateVotesAndTotalVotesTrigger;
import edu.brown.benchmark.voterwintimesstorefullstream.procedures.UpdateLeaderBoard;
import edu.brown.benchmark.voterwintimesstorefullstream.procedures.ValidatePhoneNumberTrigger;
import edu.brown.benchmark.voterwintimesstorefullstream.procedures.ValidateState;
import edu.brown.benchmark.voterwintimesstorefullstream.procedures.Vote; 
import edu.brown.benchmark.voterwintimesstorefullstream.procedures.Initialize; 

public class VoterWinTimeSStoreFullStreamProjectBuilder extends AbstractProjectBuilder {

    // REQUIRED: Retrieved via reflection by BenchmarkController
    public static final Class<? extends BenchmarkComponent> m_clientClass = VoterWinTimeSStoreFullStreamClient.class;

    // REQUIRED: Retrieved via reflection by BenchmarkController
    public static final Class<? extends BenchmarkComponent> m_loaderClass = VoterWinTimeSStoreFullStreamLoader.class;

	// a list of procedures implemented in this benchmark
    @SuppressWarnings("unchecked")
    public static final Class<? extends VoltProcedure> PROCEDURES[] = (Class<? extends VoltProcedure>[])new Class<?>[]{
        Vote.class, 
        Initialize.class,
        ValidateContestantsTrigger.class,
        UpdateVotesAndTotalVotesTrigger.class,
        UpdateLeaderBoard.class,
        ValidatePhoneNumberTrigger.class,
        ValidateState.class
    };
	
	{
		addTransactionFrequency(Vote.class, 100);
	}
	
	// a list of tables used in this benchmark with corresponding partitioning keys
    public static final String PARTITIONING[][] = new String[][] {
        { "votes", "phone_number" },
        { "votes_stream", "phone_number"},
        { "S1", "phone_number"},
        { "S2", "phone_number"},
        { "S3", "phone_number"},
        { "W_ROWS", "phone_number"},
        { "leaderboard", "contestant_number"}
    };

    public VoterWinTimeSStoreFullStreamProjectBuilder() {
        super("voterwintimesstorefullstream", VoterWinTimeSStoreFullStreamProjectBuilder.class, PROCEDURES, PARTITIONING);
    }
}




