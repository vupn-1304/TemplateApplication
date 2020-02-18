package com.pv.demoapp.utils

import android.app.Service
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Environment
import android.text.SpannableString
import android.text.style.UnderlineSpan
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.text.HtmlCompat
import androidx.fragment.app.Fragment
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.androidnetworking.error.ANError
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions.withCrossFade
import com.bumptech.glide.request.RequestOptions
import com.pv.demoapp.DemoApplication
import com.pv.demoapp.R
import com.pv.demoapp.base.BaseViewModel
import com.pv.demoapp.data.remote.ApiConstant
import com.pv.demoapp.utils.event.EventUnAuthen
import com.pv.demoapp.utils.widget.AppUtils
import com.utils.LogUtil
import com.utils.UIHelper
import com.utils.ext.isConnectedInternet
import com.widget.Boast
import de.hdodenhof.circleimageview.CircleImageView
import org.greenrobot.eventbus.EventBus
import java.io.File
import java.io.FileOutputStream
import java.net.HttpURLConnection
import java.text.NumberFormat
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

fun ImageView.showFitXY(url: String?) {
    Glide.with(DemoApplication.getInstance())
            .load(url)
            .into(this)
}

fun ImageView.show(url: String?) {
    val requestOptions = RequestOptions()
            .error(R.drawable.ic_app)
            .centerCrop()
    Glide.with(DemoApplication.getInstance())
            .load(url).apply(requestOptions)
            .transition(withCrossFade())
            .override(UIHelper.getScreenWidth(DemoApplication.getInstance()), 300)
            .into(this)
}

fun ImageView.showCenterInside(url: String?) {
    val requestOptions = RequestOptions()
            .placeholder(R.drawable.ic_app)
            .error(R.drawable.ic_app)
            .centerInside()
    Glide.with(DemoApplication.getInstance())
            .load(url).apply(requestOptions)
            .transition(withCrossFade())
            .override(UIHelper.getScreenWidth(DemoApplication.getInstance()), 300)
            .into(this)
}

fun ImageView.showCenterInsideFullHeight(url: String?) {
    val requestOptions = RequestOptions()
            .error(R.drawable.ic_app)
            .centerInside()
    Glide.with(DemoApplication.getInstance())
            .load(url).apply(requestOptions)
            .transition(withCrossFade())
            .override(UIHelper.getScreenWidth(DemoApplication.getInstance()), UIHelper.getScreenHeight(DemoApplication.getInstance()))
            .into(this)
}

fun CircleImageView.show(url: String?) {
    val requestOptions = RequestOptions()
            .error(R.drawable.ic_app)
            .centerCrop()
    Glide.with(DemoApplication.getInstance())
            .load(url).apply(requestOptions)
            //            .transition(withCrossFade())
//            .override(400, 300)
            .into(this)
}

fun ImageView.show(url: Int) {
    val requestOptions = RequestOptions()
            .error(R.drawable.ic_app)
            .centerCrop()
    Glide.with(DemoApplication.getInstance())
            .load(url).apply(requestOptions)
//            .override(400, 300)
            .transition(withCrossFade())
            .into(this)
}

fun Date?.formatDateSendServer(): String {
    return try {
        val output = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.getDefault())
        output.format(this)
    } catch (e: Exception) {
        ""
    }
}

fun Date?.getDateDDMMMYYYY(): String {
    return try {
        val df = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
        df.format(this)
    } catch (e: Exception) {
        ""
    }
}

fun Date?.getDateHHmm(): String {
    return try {
        val df = SimpleDateFormat("HH:mm", Locale.getDefault())
        df.format(this)
    } catch (e: Exception) {
        ""
    }
}

fun Date.convertDateToDetail(): String {
    val currDate = Calendar.getInstance()
    currDate.time = this
    val tmpThu = if (currDate.get(Calendar.DAY_OF_WEEK) == 1) "Chủ nhật" else "Thứ " + currDate.get(Calendar.DAY_OF_WEEK)
    return tmpThu + ", Ngày " + currDate.get(Calendar.DAY_OF_MONTH) + "," +
            " Tháng " + (currDate.get(Calendar.MONTH) + 1) + ", Năm " + currDate.get(Calendar.YEAR)
}

fun String.formatDateSendServer(): String {
    val dt = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
    val date: Date?
    try {
        date = dt.parse(this)
        val dt1 = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.getDefault())
        return dt1.format(date)
    } catch (e: ParseException) {
        e.printStackTrace()
    } catch (e: NullPointerException) {
        e.printStackTrace()
    }
    return ""
}


fun String.formatToDateSendServer(): String {
    val dt = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
    val date: Date?
    try {
        date = dt.parse(this)
        val dt1 = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        return dt1.format(date) + "T23:59:59.000Z"
    } catch (e: ParseException) {
        e.printStackTrace()
    } catch (e: NullPointerException) {
        e.printStackTrace()
    }
    return ""
}

fun String.formatDDMMYYToDate(): Date {
    val dt = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
    try {
        return dt.parse(this)
    } catch (e: ParseException) {
        e.printStackTrace()
    } catch (e: NullPointerException) {
        e.printStackTrace()
    }
    return Date()
}

fun Date?.newFormat(): Date {
    val sdf = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
    return sdf.parse(this.getDateDDMMMYYYY())
}

fun Date.compareWithToday(): Int {
    val date = Calendar.getInstance().time.newFormat()
    val curr = this.convertDateToCalendar().time.newFormat()
    return when {
        curr.after(date) -> 1
        curr.before(date) -> -1
        else -> 0
    }
}

fun String.convertDateServerToCalendar(): Calendar {
    val dt = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.getDefault())
    val date: Date?
    try {
        dt.timeZone = TimeZone.getTimeZone("UTC")
        date = dt.parse(this)
        val calendar = Calendar.getInstance()
        calendar.time = date
        return calendar
    } catch (e: ParseException) {
        e.printStackTrace()
    } catch (e: NullPointerException) {
        e.printStackTrace()
    }
    return Calendar.getInstance()
}

fun Date?.convertDateToCalendar(): Calendar {
    val calendar = Calendar.getInstance()
//    calendar.timeZone = TimeZone.getTimeZone("UTC")
    calendar.time = this
    return calendar
}

fun String.formatDecimal(): String {
    return NumberFormat.getInstance().format(this.toFloat())
}

//fun View.clickWithDebounce(debounceTime: Long = 600L, action: (view: View) -> Unit): Disposable =
//        RxView.clicks(this)
//                .throttleFirst(debounceTime, TimeUnit.MILLISECONDS, AndroidSchedulers.mainThread())
//                .subscribe { action(this) }

fun Pair<Date?, Date?>.let(action: (pair: Pair<Date?, Date?>) -> Unit) {
    if (this.first != null && this.second != null) action(this)
}

fun postSticky(event: Any) {
    EventBus.getDefault().postSticky(event)
}

fun removeAllStickyEvents() {
    EventBus.getDefault().removeAllStickyEvents()
}

fun removeStickyEvents(event: Any) {
    EventBus.getDefault().removeStickyEvent(event)
}

fun postNormal(event: Any) {
    EventBus.getDefault().post(event)
}

fun register(subscriber: Any) {
    EventBus.getDefault().register(subscriber)
}

fun unregister(subscriber: Any) {
    EventBus.getDefault().unregister(subscriber)
}

fun Throwable?.getErrorMsg(): String = try {
    when {
        this is ANError -> {
            when {
                this.errorCode == HttpURLConnection.HTTP_UNAUTHORIZED -> {
                    EventBus.getDefault().post(EventUnAuthen())
                    DemoApplication.getInstance().getString(R.string.session_expired)
                }
                this.errorCode == 0 -> ApiConstant.ERR_SOCKET_TIMEOUT
                else -> this.errorBody
            }
        }
        this?.message != null -> this.message!!
        else -> ApiConstant.UNDEFINED
    }
} catch (e: Exception) {
    ApiConstant.UNDEFINED
}

fun Fragment.toast(msg: String) {
    context?.let {
        Boast.makeText(it, msg).show()
    }
}

fun Fragment.isNoInternet(): Boolean {
    if (!isConnectedInternet()) {
        toast(DemoApplication.getInstance().getString(R.string.no_internet_connection))
        return true
    }
    return false
}

fun BaseViewModel<*>.logErr(msg: String) {
    LogUtil.error(this.javaClass.simpleName, msg)
}

fun TextView.setUnderline(text: String) {
    val spannableString = SpannableString(text)
    spannableString.setSpan(UnderlineSpan(), 0, text.length, 0)
    this.text = spannableString
}

fun AlertDialog.initDialog() {
    this.setCancelable(false)
    this.window?.let {
        it.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        it.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        it.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
    }
}

fun Fragment.removeLastFragmentInBackStack(container: Int) {
    val fm = childFragmentManager
    val count = fm.backStackEntryCount
    for (i in 0 until count) {
        fm.popBackStackImmediate()
        break
    }
}

fun loadStringRes(resId: Int): String = DemoApplication.getInstance().getString(resId)

fun SwipeRefreshLayout.cancelRefresh() {
    try {
        this.isRefreshing = false
    } catch (e: Exception) {
        e.printStackTrace()
    }
}

fun Fragment.logErr(msg: String) {
    LogUtil.error(this::class.java.simpleName, msg)
}

fun Any.logErr(msg: String) {
    LogUtil.error(this.javaClass.simpleName, msg)
}

fun Service.logErr(msg: String) {
    LogUtil.error(this::class.java.simpleName, msg)
}

fun TextView.setHtml(htmlText: String) {
//    val font = Typeface.createFromAsset(context!!.assets, "fonts/segui/seguisb_regular.ttf")
//    val font2 = Typeface.createFromAsset(context!!.assets, "fonts/segui/seguisb_bold.ttf")
//    val spannableStringBuilder = SpannableStringBuilder("$normal $bold")
//    if (isRevese) {
//        spannableStringBuilder.setSpan(CustomTypefaceSpan(font2), 0, normal.length, Spanned
//                .SPAN_EXCLUSIVE_INCLUSIVE)
//    } else {
//        spannableStringBuilder.setSpan(CustomTypefaceSpan(font2), normal.length, normal.length + bold.length + 1, Spanned.SPAN_EXCLUSIVE_INCLUSIVE)
//    }
////        spannableStringBuilder.setSpan(CustomTypefaceSpan(font), 4, 11, Spanned.SPAN_EXCLUSIVE_INCLUSIVE)
//
//    this.text = spannableStringBuilder
//    val styledText = context.getString(R.string.ff);
    text = HtmlCompat.fromHtml(htmlText, HtmlCompat.FROM_HTML_MODE_LEGACY)
}

fun AppCompatActivity.logErr(msg: String) {
    LogUtil.error(this::class.java.simpleName, msg)
}

fun BaseViewModel<*>.isConnectedInternet() = AppUtils.isConnectedInternet(DemoApplication.getInstance())

fun writeHtmlToFile(html: String): File {
    val dir = Environment.getExternalStorageDirectory().path + File.separator + "HTML_TEST" + File.separator
    val file = File(dir)
    if (!file.exists()) {
        file.mkdir()
    }

    val htmlFile = File(dir + "html_test.html")
    if (htmlFile.exists()) {
        htmlFile.delete()
    }

    htmlFile.createNewFile()

    val tream = FileOutputStream(htmlFile)
    try {
        tream.write(html.toByteArray())
    } catch (e: Exception) {
        e.printStackTrace()
    } finally {
        tream.close()
    }
    return htmlFile
}
