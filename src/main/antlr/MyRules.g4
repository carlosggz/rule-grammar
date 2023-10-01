grammar MyRules;

fragment LOWERCASE: [a-z] ;
fragment UPPERCASE: [A-Z] ;
fragment DIGIT: [0-9] ;
WORD: (LOWERCASE | UPPERCASE)+ ;
TEXT: '"' .*? '"' ;
WHITESPACE: (' '|'t')+ -> skip ;

INTEGER: DIGIT+;

NEWLINE: '\r\n'|'\n' ;
FIELD_NAME: ('/' (WORD | INTEGER))+;

number_equals: 'equals to' INTEGER;
greater_than: 'greater than' INTEGER;
less_than: 'less than' INTEGER;
between: 'between' INTEGER 'and' INTEGER;

number_criteria: (number_equals | greater_than | less_than | between) ;
string_criteria: ('starts with' | 'ends with' | 'contains') TEXT;
not_null_criteria: 'is not null';
is_null_criteria: 'is null';

criteria: string_criteria | number_criteria | not_null_criteria | is_null_criteria;

line: 'field' FIELD_NAME criteria ('and' criteria)+?;
filter: (line NEWLINE)+ EOF;
