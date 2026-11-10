
ALTER TABLE buildings
    ADD COLUMN owner_id BIGINT;

ALTER TABLE buildings
    ADD CONSTRAINT fk_building_owner
        FOREIGN KEY (owner_id)
            REFERENCES users(id);

CREATE INDEX idx_buildings_owner_id
    ON buildings(owner_id);
