package com.lqh.lichao.chinamap;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.graphics.Region;

/**
 * 一个省的Bean类
 * Created by Administrator on 2017-10-14.
 */

public class ProvinceItem {
    /**
     * 区域路径
     */
    private Path path;

    /**
     * 区域背景色，默认白色
     */
    private int drawColor = Color.WHITE;

    /**
     * 区域省份名称
     */
    private String provinceName;

    /**
     * 区域省份编码
     */
    private int provinceCode;

    /**
     * 区域省份人数
     */
    private int personNumber;

    /**
     * 区域绘制方法
     *
     * @param canvas     画布
     * @param paint      画笔
     * @param isSelected 是否选中
     */
    public void drawItem(Canvas canvas, Paint paint, boolean isSelected) {

        //选中时绘制阴影描边效果
        if (isSelected) {
            paint.setStrokeWidth(2);
            paint.setColor(Color.BLACK);
            paint.setStyle(Paint.Style.FILL);
            paint.setShadowLayer(8, 0, 0, 0xFFFFFFFF);
            canvas.drawPath(path, paint);

            paint.clearShadowLayer();
            paint.setColor(drawColor);
            paint.setStyle(Paint.Style.FILL);
            paint.setStrokeWidth(2);
            canvas.drawPath(path, paint);

        } else {
            //非选中时，绘制描边效果
            paint.clearShadowLayer();
            paint.setStrokeWidth(1);
            paint.setStyle(Paint.Style.FILL);
            paint.setColor(drawColor);
            canvas.drawPath(path, paint);

            paint.setStyle(Paint.Style.STROKE);
            int strokeColor = 0xFFD0E8F4;
            paint.setColor(strokeColor);
            canvas.drawPath(path, paint);
        }
    }

    /**
     * 判断该区域是否处于touch状态
     *
     * @param x 当前x
     * @param y 当前y
     * @return 是否处于touch状态
     */
    public boolean isTouched(int x, int y) {
        RectF r = new RectF();
        path.computeBounds(r, true);

        Region region = new Region();
        region.setPath(path, new Region((int) r.left, (int) r.top, (int) r.right, (int) r.bottom));
        return region.contains(x, y);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ProvinceItem item = (ProvinceItem) o;
        return provinceCode == item.provinceCode;
    }

    @Override
    public int hashCode() {
        return provinceCode;
    }

    public Path getPath() {
        return path;
    }

    public void setPath(Path path) {
        this.path = path;
    }

    public int getDrawColor() {
        return drawColor;
    }

    public void setDrawColor(int drawColor) {
        this.drawColor = drawColor;
    }

    public String getProvinceName() {
        return provinceName;
    }

    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
    }

    public int getProvinceCode() {
        return provinceCode;
    }

    public void setProvinceCode(int provinceCode) {
        this.provinceCode = provinceCode;
    }

    public int getPersonNumber() {
        return personNumber;
    }

    public void setPersonNumber(int personNumber) {
        this.personNumber = personNumber;
    }
}
