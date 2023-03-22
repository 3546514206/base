LinearLayout布局绘制相关源码解析
===

## 1. onMeasure

 首先根据布局的方向调用不同的measure方法

```java
protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        if (mOrientation == VERTICAL) {
            measureVertical(widthMeasureSpec, heightMeasureSpec);
        } else {
            measureHorizontal(widthMeasureSpec, heightMeasureSpec);
        }
    }
```
因为两个方向的方法实现比较类似，仅以第一个展开讲解（以下源码均源自measureVertical方法）

## 2. measureVertical方法
该方法的方法主要可以分成两个维度去解读，第一个维度是具体数值和weight数值各自的测量方案；第二个维度是高度和宽度各自的测绘。

由于是横向的布局，所以重心在height的测量上。

### 2.1 特殊处理（跳过第一次测量）
对于EXACT mode且使用weight的子View进行特殊处理：跳过第一次测量
```java
final boolean useExcessSpace = lp.height == 0 && lp.weight > 0;
if (heightMode == MeasureSpec.EXACTLY && useExcessSpace) {
    final int totalLength = mTotalLength;
    mTotalLength = Math.max(totalLength, totalLength + lp.topMargin + lp.bottomMargin);
    // 当mode为EXACT且使用weight测量时跳过预先测量
    skippedMeasure = true;
}
```

### 2.2 第一次测量
1. 测量了2.1所提View之外的剩下子View，measureChildBeforeLayout方法最终会执行到ViewGroup里的view.measure方法。
```java
final int usedHeight = totalWeight == 0 ? mTotalLength : 0;
measureChildBeforeLayout(child, i, widthMeasureSpec, 0, heightMeasureSpec, usedHeight);
```
2. 完成第一次测量后，更新weight View占据的高度、所有控件高度等信息。
```java
final int childHeight = child.getMeasuredHeight();
if (useExcessSpace) {
    lp.height = 0;
    consumedExcessSpace += childHeight;
}
final int totalLength = mTotalLength;
mTotalLength = Math.max(totalLength, totalLength + childHeight + lp.topMargin + lp.bottomMargin + getNextLocationOffset(child));
```
3. 宽度处理
首先更新了match_parent的情况，其中matchWidth变量直接决定了是否进行第三次测量。
```java
boolean matchWidthLocally = false;//当前View是否可以MatchParent
if (widthMode != MeasureSpec.EXACTLY && lp.width == LayoutParams.MATCH_PARENT) {
    matchWidth = true;
    matchWidthLocally = true;
}
```
4. 分开保存固定高度的View和weight类型的View的宽度
```java
final int margin = lp.leftMargin + lp.rightMargin;
final int measuredWidth = child.getMeasuredWidth() + margin;
maxWidth = Math.max(maxWidth, measuredWidth);
//...
if (lp.weight > 0) {
weightedMaxWidth = Math.max(weightedMaxWidth,
                        matchWidthLocally ? margin : measuredWidth);
} else {
lternativeMaxWidth = Math.max(alternativeMaxWidth,
                        matchWidthLocally ? margin : measuredWidth);
}
```

### 2.3 第二次测量
1. 测量前统计了剩下的属于weight类型子View的长度。第二次测量的进入条件是之前存在跳过测量的View或者存在遗留的weight长度。
```java
int remainingExcess = heightSize - mTotalLength
    + (mAllowInconsistentMeasurement ? 0 : consumedExcessSpace);
 //处理之前存在跳过测量的子View或存在遗留的weight长度的情况
if (skippedMeasure|| ((sRemeasureWeightedChildren || remainingExcess != 0) && totalWeight > 0.0f)) {
    //...
}
```
2. 下面是对weight类型View的高度测量与处理
```java
if (childWeight > 0) {
//计算并减去该子View要分掉的长度
final int share = (int) (childWeight * remainingExcess / remainingWeightSum);
remainingExcess -= share;
remainingWeightSum -= childWeight;

//完成子Viwe实际高度的赋值
final int childHeight;
if (mUseLargestChild && heightMode != MeasureSpec.EXACTLY) {
    childHeight = largestChildHeight;
} else if (lp.height == 0 && (!mAllowInconsistentMeasurement
                            || heightMode == MeasureSpec.EXACTLY)) {               
    childHeight = share;
} else {           
    childHeight = child.getMeasuredHeight() + share;
}

//这里做了实际的子View的measure
final int childHeightMeasureSpec = MeasureSpec.makeMeasureSpec(
    Math.max(0, childHeight), MeasureSpec.EXACTLY);
final int childWidthMeasureSpec = getChildMeasureSpec(widthMeasureSpec, mPaddingLeft + mPaddingRight + lp.leftMargin + lp.rightMargin,lp.width);
child.measure(childWidthMeasureSpec,childHeightMeasureSpec);      
}
```
3. 对所有View的宽度进行第二次处理
```java
final int margin =  lp.leftMargin + lp.rightMargin;
final int measuredWidth = child.getMeasuredWidth() + margin;
maxWidth = Math.max(maxWidth, measuredWidth);

boolean matchWidthLocally = widthMode != MeasureSpec.EXACTLY &&
                        lp.width == LayoutParams.MATCH_PARENT;

alternativeMaxWidth = Math.max(alternativeMaxWidth,
                        matchWidthLocally ? margin : measuredWidth);

allFillParent = allFillParent && lp.width == LayoutParams.MATCH_PARENT;

final int totalLength = mTotalLength;
mTotalLength = Math.max(totalLength, totalLength + child.getMeasuredHeight() + lp.topMargin + lp.bottomMargin + getNextLocationOffset(child));
```
4. 处理与绑定测绘结果
```java
if (!allFillParent && widthMode != MeasureSpec.EXACTLY) {
    maxWidth = alternativeMaxWidth;
}

maxWidth += mPaddingLeft + mPaddingRight;

maxWidth = Math.max(maxWidth, getSuggestedMinimumWidth());

setMeasuredDimension(resolveSizeAndState(maxWidth, widthMeasureSpec, childState), heightSizeAndState);
```


### 2.4 第三次测量
```java
if (matchWidth) {
    forceUniformWidth(count, heightMeasureSpec);
}

private void forceUniformWidth(int count, int heightMeasureSpec) {
    int uniformMeasureSpec = MeasureSpec.makeMeasureSpec(getMeasuredWidth(), MeasureSpec.EXACTLY);
    for (int i = 0; i< count; ++i) {
        final View child = getVirtualChildAt(i);
        if (child != null && child.getVisibility() != GONE) {
               LinearLayout.LayoutParams lp = ((LinearLayout.LayoutParams)child.getLayoutParams());

            if (lp.width == LayoutParams.MATCH_PARENT) {
                int oldHeight = lp.height;
                lp.height = child.getMeasuredHeight();

                measureChildWithMargins(child, uniformMeasureSpec, 0, heightMeasureSpec, 0);
                lp.height = oldHeight;
            }
        }
    }
}
```

## 3. onLayout
与onMeasure类似，onLayout也是根据排列方向调用不同的方法的。参数里的l、t、r、b是LinearLayout相对于parent的坐标。
```java
protected void onLayout(boolean changed, int l, int t, int r, int b) {
        if (mOrientation == VERTICAL) {
            layoutVertical(l, t, r, b);
        } else {
            layoutHorizontal(l, t, r, b);
        }
    }
```
接下来也是同样仅讲解vertical的情况。

## 4. layoutVertical方法
1. 在layout子View前先确定top坐标
```java
switch (majorGravity) {
           case Gravity.BOTTOM:
               // mTotalLength contains the padding already
               childTop = mPaddingTop + bottom - top - mTotalLength;
               break;

               // mTotalLength contains the padding already
           case Gravity.CENTER_VERTICAL:
               childTop = mPaddingTop + (bottom - top - mTotalLength) / 2;
               break;

           case Gravity.TOP:
           default:
               childTop = mPaddingTop;
               break;
        }
```
2. 遍历并测量子View
```java
//首先确定子View的left坐标
switch (absoluteGravity & Gravity.HORIZONTAL_GRAVITY_MASK) {
                    case Gravity.CENTER_HORIZONTAL:
                        childLeft = paddingLeft + ((childSpace - childWidth) / 2)
                                + lp.leftMargin - lp.rightMargin;
                        break;

                    case Gravity.RIGHT:
                        childLeft = childRight - childWidth - lp.rightMargin;
                        break;

                    case Gravity.LEFT:
                    default:
                        childLeft = paddingLeft + lp.leftMargin;
                        break;
}
//...
//确定子View的top坐标
childTop += lp.topMargin;
//里面就是调用子View的layout方法
setChildFrame(child, childLeft, childTop + getLocationOffset(child),
        childWidth, childHeight);
//更新下一个子View的top坐标
childTop += childHeight + lp.bottomMargin + getNextLocationOffset(child);

```

## 5. onDraw
按方向划分的思想一以贯之
```java
protected void onDraw(Canvas canvas) {
        if (mDivider == null) {
            return;
        }

        if (mOrientation == VERTICAL) {
            drawDividersVertical(canvas);
        } else {
            drawDividersHorizontal(canvas);
        }
    }
```
它干的事情就是绘制分割线，代码很清晰就不展开了
```java
final int count = getVirtualChildCount();
        for (int i = 0; i < count; i++) {
            final View child = getVirtualChildAt(i);
            if (child != null && child.getVisibility() != GONE) {
                if (hasDividerBeforeChildAt(i)) {
                    final LayoutParams lp = (LayoutParams) child.getLayoutParams();
                    final int top = child.getTop() - lp.topMargin - mDividerHeight;
                    drawHorizontalDivider(canvas, top);
                }
            }
        }

        if (hasDividerBeforeChildAt(count)) {
            final View child = getLastNonGoneChild();
            int bottom = 0;
            if (child == null) {
                bottom = getHeight() - getPaddingBottom() - mDividerHeight;
            } else {
                final LayoutParams lp = (LayoutParams) child.getLayoutParams();
                bottom = child.getBottom() + lp.bottomMargin;
            }
            drawHorizontalDivider(canvas, bottom);
        }
```
子View的draw()是在ViewGroup类中drawChild()调用的，而drawChild则是在dispatchDraw()中调用，这个dispatch方法是个View中预留的模板方法。这个模板方法在View中有多处有调用，根据View的官方注释，是通过boolean draw(Canvas canvas, ViewGroup parent, long drawingTime)这个方法让子View绘制自己的。

