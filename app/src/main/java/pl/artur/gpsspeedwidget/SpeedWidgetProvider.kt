package pl.artur.gpsspeedwidget

import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.ComponentName
import android.content.Context
import android.widget.RemoteViews

object SpeedWidgetProvider {
    fun render(context: Context, speedKmh: Int) {
        val manager = AppWidgetManager.getInstance(context)
        val component = ComponentName(context, SpeedWidgetProviderImpl::class.java)
        val ids = manager.getAppWidgetIds(component)
        if (ids.isEmpty()) return

        val views = RemoteViews(context.packageName, R.layout.widget_speed)
        views.setTextViewText(R.id.speed_value, speedKmh.toString())
        views.setTextViewText(R.id.speed_unit, "km/h")
        manager.updateAppWidget(ids, views)
    }
}

class SpeedWidgetProviderImpl : AppWidgetProvider() {
    override fun onUpdate(context: Context, manager: AppWidgetManager, ids: IntArray) {
        SpeedWidgetProvider.render(context, 0)
    }
}
