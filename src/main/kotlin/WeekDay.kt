enum class WeekDay(val dayNumber: Int) {
    Monday(1), Tuesday(2), Wednesday(3), Thursday(4),
    Friday(5), Saturday(6), Sunday(7)
}

val WeekDays: List<WeekDay> = WeekDay.values().toList()
