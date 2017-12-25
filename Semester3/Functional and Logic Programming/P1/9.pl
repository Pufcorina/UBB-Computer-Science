% 9.
% a. Insert an element on the position n in a list.
% b. Define a predicate to determine the greatest common divisor of all numbers from a list

% a

% Model matematic:
% insert_value_in_list_on_position(l1...ln, e, p) =
% 	e + l1...ln, p = 0
% 	l1 + insert_value_in_list_on_position(l2...ln, e, p - 1), otherwise

% insert_value_in_list_on_position(L:list, E:number, P:number, R:list)
% insert_value_in_list_on_position(i, i, i, o)
insert_value_in_list_on_position(L, E, 0, [E|L]):-!.
insert_value_in_list_on_position([H|T], E, P, [H|R]):-
    P1 is P - 1,
    insert_value_in_list_on_position(T, E, P1, R).

% b
 
% Model matematic:
% gcd(a, b) =
%	a, b = 0
%	b, a = 0
%	gcd(a % b, b), a >= b
%	gcd(a, b % a), a < b

% gcd(A:number, B:number, R:number)
% gcd(i, i, o)

gcd(0, B, B) :- !.
gcd(A, 0, A) :- !.
gcd(A, B, R) :- A >= B,
    A1 is A mod B,
    gcd(A1, B, R), !.
gcd(A, B, R) :- A < B,
    B1 is B mod A,
    gcd(A, B1, R), !.

% Model matematic:
% gcd_list(l1...ln) =
% 	l1, n = 1
% 	gcd(l1, gcd_list(l2...ln)), otherwise
	
% gcd_list(L:list, R:number)
% gcd_list(i, o)

gcd_list([H], H).
gcd_list([H|T], R) :- 
    gcd_list(T, R1),
    gcd(R1, H, R).