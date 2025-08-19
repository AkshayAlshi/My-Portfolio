-- Basic DB validation scripts
-- 1) Verify user exists
SELECT id, name, email FROM users WHERE email = 'test@example.com';

-- 2) Verify order totals
SELECT o.id, SUM(oi.quantity * oi.unit_price) AS calc_total, o.total_amount
FROM orders o
JOIN order_items oi ON oi.order_id = o.id
GROUP BY o.id
HAVING calc_total <> o.total_amount;

-- 3) Data integrity: orphans
SELECT oi.* FROM order_items oi
LEFT JOIN orders o ON o.id = oi.order_id
WHERE o.id IS NULL;
