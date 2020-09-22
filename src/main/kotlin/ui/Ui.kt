package ui

import WeeklyMeals
import java.awt.*
import javax.swing.JFrame
import javax.swing.JPanel
import javax.swing.WindowConstants


fun printDiet(weeklyMeals: WeeklyMeals) {
    with(JFrame()) {
        title = "Test Frame"
        defaultCloseOperation = WindowConstants.EXIT_ON_CLOSE
        setSize(1600, 500)
        isResizable = false

        add(DietComponent(weeklyMeals))

        setLocationRelativeTo(null)
        isVisible = true
    }
}

private val FONT_NAME = "Arial"
private val TILE_SIZE = 32
private val TILES_MARGIN = 16

class DietComponent(val weeklyMeals: WeeklyMeals) : JPanel() {

    private val proteinBackgroundColor = Color(0xFFFC8A3)
    private val proteinTextColor = Color(0xF5483A7)

    private val vegetableBackgroundColor = Color(0xFCCFFD5)
    private val vegetableTextColor = Color(0xF5483A7)

    private val sideVegetableBackgroundColor = Color(0xFA2CEA8)
    private val sideVegetableTextColor = Color(0xF5483A7)

    private val carbBackgroundColor = Color(0xFFFF7CC)
    private val carbTextColor = Color(0xF5483A7)

    override fun paint(g: Graphics) {
        super.paint(g)
        g.color = Color(0xFEFFCFA)
        g.fillRect(0, 0, this.size.width, this.size.height)
        drawLunchLabel((g as Graphics2D))
        drawDinnerLabel(g)
        for ((index, pair) in weeklyMeals.meals.withIndex()) {
            drawDay(g, "${pair.first}", (index + 1) * 2, 0)
            // lunch
            drawMealNutrient(g, pair.second.lunch.protein.toString(), (index + 1) * 2, 1, proteinBackgroundColor, proteinTextColor)
            drawMealNutrient(g, pair.second.lunch.vegetable.toString(), (index + 1) * 2, 2, vegetableBackgroundColor, vegetableTextColor)
            drawMealNutrient(g, pair.second.lunch.sideVegetables.toString(), (index + 1) * 2, 3, sideVegetableBackgroundColor, sideVegetableTextColor)
            drawMealNutrient(g, pair.second.lunch.carb.toString(), (index + 1) * 2, 4, carbBackgroundColor, carbTextColor)
            // dinner
            drawMealNutrient(g, pair.second.dinner.protein.toString(), (index + 1) * 2, 5, proteinBackgroundColor, proteinTextColor)
            drawMealNutrient(g, pair.second.dinner.vegetable.toString(), (index + 1) * 2, 6, vegetableBackgroundColor, vegetableTextColor)
            drawMealNutrient(g, pair.second.dinner.sideVegetables.toString(), (index + 1) * 2, 7, sideVegetableBackgroundColor, sideVegetableTextColor)
            drawMealNutrient(g, pair.second.dinner.carb.toString(), (index + 1) * 2, 8, carbBackgroundColor, carbTextColor)
        }
    }
}

private fun drawLunchLabel(g: Graphics2D) {
    g.color = Color(0xFF1E0FF)
    g.fillRoundRect(TILES_MARGIN * 5, TILES_MARGIN * 3, 1500, 200, 50, 50)

    g.color = Color.black
    g.drawRoundRect(TILES_MARGIN * 5, TILES_MARGIN * 3, 1500, 200, 50, 50)

    g.color = Color(0x545AA7)
    g.font = Font(FONT_NAME, Font.ITALIC, 19)
//    g.drawString("LUNCH", TILES_MARGIN * 6, TILES_MARGIN * 10)
    g.drawString("LUNCH", TILES_MARGIN / 2, TILES_MARGIN * 10)

    g.font = Font(FONT_NAME, Font.ITALIC, 15)
    g.drawString("protein", TILES_MARGIN * 6, offsetCoors(1) + TILES_MARGIN + 4)
    g.drawString("vegetable", TILES_MARGIN * 6, offsetCoors(2) + TILES_MARGIN + 4)
    g.drawString("side vegetables", TILES_MARGIN * 6, offsetCoors(3) + TILES_MARGIN + 4)
    g.drawString("carbohydrate", TILES_MARGIN * 6, offsetCoors(4) + TILES_MARGIN + 4)
}

private fun drawDinnerLabel(g: Graphics2D) {
    g.color = Color(0xFFFE4E4)
    g.fillRoundRect(TILES_MARGIN * 5, TILES_MARGIN * 15 + (TILES_MARGIN / 2), 1500, 200, 50, 50)

    g.color = Color.black
    g.drawRoundRect(TILES_MARGIN * 5, TILES_MARGIN * 15 + (TILES_MARGIN / 2), 1500, 200, 50, 50)

    g.color = Color(0x545AA7)
    g.font = Font(FONT_NAME, Font.ITALIC, 19)
//    g.drawString("DINNER", TILES_MARGIN * 6, TILES_MARGIN * 22)
    g.drawString("DINNER", TILES_MARGIN / 3, TILES_MARGIN * 22)

    g.font = Font(FONT_NAME, Font.ITALIC, 15)
    g.drawString("protein", TILES_MARGIN * 6, offsetCoors(5) + TILES_MARGIN + 4)
    g.drawString("vegetable", TILES_MARGIN * 6, offsetCoors(6) + TILES_MARGIN + 4)
    g.drawString("side vegetables", TILES_MARGIN * 6, offsetCoors(7) + TILES_MARGIN + 4)
    g.drawString("carbohydrate", TILES_MARGIN * 6, offsetCoors(8) + TILES_MARGIN + 4)
}


private fun drawDay(g: Graphics2D, value: String, x: Int, y: Int) {
    g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON)
    g.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_NORMALIZE)

    val xOffset = offsetCoors(x)
    val yOffset = offsetCoors(y)
    g.color = Color(0xCCCCFF)
    g.fillRoundRect(xOffset * 2, yOffset / 2, TILE_SIZE * 5 + TILES_MARGIN, TILE_SIZE + TILES_MARGIN, 14, 14)
    g.color = Color(0x545AA7)
    val size = 24
    val font = Font(FONT_NAME, Font.BOLD, size)
    g.font = font
    g.drawString(value, (xOffset * 2) + TILES_MARGIN, yOffset * 2 + (TILES_MARGIN / 2))
}

private fun drawMealNutrient(g: Graphics2D, nutrient: String, x: Int, y: Int, backgroundColor: Color, textColor: Color) {
    g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON)
    g.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_NORMALIZE)

    val xOffset = offsetCoors(x)
    val yOffset = offsetCoors(y)
    g.color = backgroundColor
    g.fillRoundRect(xOffset * 2, yOffset, TILE_SIZE * 5 + TILES_MARGIN, TILE_SIZE, 14, 14)
    g.color = textColor
    val size = if (nutrient.length > 24) 10 else if(nutrient.length > 19) 13 else 15
    val font = Font("Verdana", Font.PLAIN, size)
    g.font = font

    g.drawString(nutrient, (xOffset * 2) + (TILES_MARGIN/2), yOffset + TILES_MARGIN + 4)
}


private fun offsetCoors(arg: Int): Int {
    return arg * (TILES_MARGIN + TILE_SIZE) + TILES_MARGIN
}
