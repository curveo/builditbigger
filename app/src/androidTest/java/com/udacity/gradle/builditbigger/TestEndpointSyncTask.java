package com.udacity.gradle.builditbigger;

import android.test.AndroidTestCase;
import com.udacity.gradle.builditbigger.EndpointsAsyncTask.PostEndpointTask;

import java.util.concurrent.CountDownLatch;

/**
 * Created by curtis on 11/4/15.
 */
public class TestEndpointSyncTask extends AndroidTestCase {

    CountDownLatch mLatch;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        mLatch = new CountDownLatch(1);
    }

    public void testTaskReturnsNonEmptyString() throws InterruptedException {
        EndpointsAsyncTask task = new EndpointsAsyncTask();
        task.execute(new EndpointsAsyncTask.PostEndpointTask(){

            @Override
            public void onPostExecute(String result) {
                assertNotNull(result);
                assertTrue(result.length() > 0);
                mLatch.countDown();
            }
        });
        mLatch.await();
    }
}
