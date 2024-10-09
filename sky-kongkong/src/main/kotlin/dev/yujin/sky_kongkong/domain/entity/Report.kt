package dev.yujin.sky_kongkong.domain.entity

import dev.yujin.sky_kongkong.domain.constant.ReportType
import jakarta.persistence.*
import java.time.LocalDateTime


@Entity
class Report(
    userId: Long,
    isSolved: Boolean,
    category: String,
    detail: String,
): BaseEntity() {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    /* auto_increment */
    @Column(name = "report_id") /* column mapping */
    var reportId: Long? = null /* spring에서 null로 보내면, DB에서 auto_increment */

    @Column(name = "is_solved")
    var isSolved: Boolean = isSolved

    @Column(name = "user_id")
    var userId: Long = userId

    @Column(name = "category")
    @Enumerated(value = EnumType.STRING)
    var category: ReportType = ReportType.valueOf(category)

    @Column(name = "detail")
    var detail: String = detail
}