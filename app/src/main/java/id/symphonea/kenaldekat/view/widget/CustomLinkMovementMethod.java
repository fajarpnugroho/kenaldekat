package id.symphonea.kenaldekat.view.widget;

import android.text.Layout;
import android.text.Selection;
import android.text.Spannable;
import android.text.method.ArrowKeyMovementMethod;
import android.text.style.ClickableSpan;
import android.view.MotionEvent;
import android.widget.TextView;

public class CustomLinkMovementMethod extends ArrowKeyMovementMethod {
    private static CustomLinkMovementMethod instance;

    public static CustomLinkMovementMethod getInstance() {
        if (instance == null) {
            instance = new CustomLinkMovementMethod();
        }
        return instance;
    }

    @Override
    public boolean onTouchEvent(TextView widget, Spannable buffer, MotionEvent event) {
        int action = event.getAction();

        // return super if event is not Up or not down
        if (!(action == MotionEvent.ACTION_UP || action == MotionEvent.ACTION_DOWN)) {
            return super.onTouchEvent(widget, buffer, event);
        }

        int x = (int) event.getX();
        int y = (int) event.getY();

        x -= widget.getTotalPaddingLeft();
        y -= widget.getTotalPaddingTop();

        x += widget.getScrollX();
        y += widget.getScrollY();

        Layout layout = widget.getLayout();
        int line = layout.getLineForVertical(y);
        int off = layout.getOffsetForHorizontal(line, x);

        ClickableSpan[] link = buffer.getSpans(off, off, ClickableSpan.class);

        if (link.length != 0) {
            if (action == MotionEvent.ACTION_UP) {
                link[0].onClick(widget);
            } else {
                Selection.setSelection(
                        buffer, buffer.getSpanStart(link[0]), buffer.getSpanEnd(link[0]));
            }

            return true;
        }

        return super.onTouchEvent(widget, buffer, event);
    }
}
