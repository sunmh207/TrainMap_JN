CREATE VIEW
    Available_Locomotive_V
    (
        ID,
        LOCOMEDEL,
        LOCONUMBER,
        OTHER,        
        LOCONUMBER_CODE,
        DIDIAN        
    ) AS
SELECT
    dict.ID,
    dict.LOCOMEDEL,
    dict.LOCONUMBER,
    dict.OTHER,        
    dict.LOCONUMBER_CODE,
    dict.DIDIAN 

from locomotive_dict dict, gudao gd
where dict.loconumber=gd.ch(+)
and gd.ch is null