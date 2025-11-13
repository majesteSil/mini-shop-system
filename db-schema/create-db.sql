-- ============================================================================
--  Create service databases for Microservice architecture
--  Databases:
--     - customers
--     - products
--     - orders
-- ============================================================================

-- Check if 'customers' database exists; create if not
DO
$$
BEGIN
    IF NOT EXISTS (
        SELECT FROM pg_database WHERE datname = 'customers'
    ) THEN
        PERFORM dblink_exec('dbname=' || current_database(), 'CREATE DATABASE customers');
    END IF;
END
$$;

-- Check if 'products' database exists; create if not
DO
$$
BEGIN
    IF NOT EXISTS (
        SELECT FROM pg_database WHERE datname = 'products'
    ) THEN
        PERFORM dblink_exec('dbname=' || current_database(), 'CREATE DATABASE products');
    END IF;
END
$$;

-- Check if 'orders' database exists; create if not
DO
$$
BEGIN
    IF NOT EXISTS (
        SELECT FROM pg_database WHERE datname = 'orders'
    ) THEN
        PERFORM dblink_exec('dbname=' || current_database(), 'CREATE DATABASE orders');
    END IF;
END
$$;
