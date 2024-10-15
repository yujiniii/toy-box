package dev.yujin.sky_kongkong.domain.entity

import dev.yujin.sky_kongkong.domain.constant.ReportType
import jakarta.persistence.*


@Entity
@Table(name = "report")
class Report(
    isSolved: Boolean,
    category: String,
    detail: String,
) : BaseEntity() {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    /* auto_increment */
    @Column(name = "report_id") /* column mapping */
    var reportId: Long? = null /* spring에서 null로 보내면, DB에서 auto_increment */

    @Column(name = "is_solved")
    var isSolved: Boolean = isSolved

    @Column(name = "category")
    @Enumerated(value = EnumType.STRING)
    var category: ReportType = ReportType.valueOf(category)

    @Column(name = "detail")
    var detail: String = detail

    @ManyToOne(targetEntity = User::class, fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    lateinit var user: User

    fun update(
        isSolved: Boolean,
        category: ReportType,
        detail: String,
    ) {
        this.isSolved = isSolved
        this.category = category
        this.detail = detail
    }
}