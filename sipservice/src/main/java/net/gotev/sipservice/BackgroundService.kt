package net.gotev.sipservice

import android.app.Service
import android.content.Intent
import android.os.*


open class BackgroundService : Service() {

    private lateinit var mWorkerThread: HandlerThread
    private lateinit var mHandler: Handler
    private lateinit var mWakeLock: PowerManager.WakeLock

    override fun onCreate() {
        super.onCreate()

        val pm = getSystemService(POWER_SERVICE) as PowerManager
        mWakeLock =
            pm.newWakeLock(PowerManager.PARTIAL_WAKE_LOCK, BackgroundService::class.simpleName)

        mWakeLock.acquire(10 * 60 * 1000L /*10 minutes*/)

        mWorkerThread = HandlerThread(
            BackgroundService::class.simpleName,
            Process.THREAD_PRIORITY_FOREGROUND
        )
        mWorkerThread.priority = Thread.MAX_PRIORITY
        mWorkerThread.start()
        mHandler = Handler(mWorkerThread.looper)
    }

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    override fun onDestroy() {
        super.onDestroy()
        mWorkerThread.quitSafely()
        mWakeLock.release()
    }

    protected fun enqueueJob(job: Runnable) {
        mHandler.post(job)
    }

    protected fun enqueueDelayedJob(job: Runnable) {
        mHandler.postDelayed(job, SipServiceConstants.DELAYED_JOB_DEFAULT_DELAY.toLong())
    }

    protected fun dequeueJob(job: Runnable) {
        mHandler.removeCallbacks(job)
    }

}
