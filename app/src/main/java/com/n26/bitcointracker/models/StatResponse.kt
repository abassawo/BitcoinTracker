package com.n26.bitcointracker.models

class StatResponse {
    var market_price_usd: String? = null

    var hash_rate: String? = null

    var total_fees_btc: String? = null

    var n_btc_mined: String? = null

    var n_tx: String? = null

    var n_blocks_mined: String? = null

    var minutes_between_blocks: String? = null

    var totalbc: String? = null

    var n_blocks_total: String? = null

    var trade_volume_usd: String? = null

    var estimated_transaction_volume_usd: String? = null

    var blocks_size: String? = null

    var miners_revenue_usd: String? = null

    var nextretarget: String? = null

    var difficulty: String? = null

    var trade_volume_btc: String? = null

    var estimated_btc_sent: String? = null

    var miners_revenue_btc: String? = null

    var total_btc_sent: String? = null

    var timestamp: String? = null

    override fun toString(): String {
        return "StatResponse [market_price_usd = $market_price_usd, hash_rate = $hash_rate, total_fees_btc = $total_fees_btc, n_btc_mined = $n_btc_mined, n_tx = $n_tx, n_blocks_mined = $n_blocks_mined, minutes_between_blocks = $minutes_between_blocks, totalbc = $totalbc, n_blocks_total = $n_blocks_total, trade_volume_usd = $trade_volume_usd, estimated_transaction_volume_usd = $estimated_transaction_volume_usd, blocks_size = $blocks_size, miners_revenue_usd = $miners_revenue_usd, nextretarget = $nextretarget, difficulty = $difficulty, trade_volume_btc = $trade_volume_btc, estimated_btc_sent = $estimated_btc_sent, miners_revenue_btc = $miners_revenue_btc, total_btc_sent = $total_btc_sent, timestamp = $timestamp]"
    }
}
