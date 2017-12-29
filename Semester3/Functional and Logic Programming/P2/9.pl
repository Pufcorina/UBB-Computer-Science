% 9.
% a. For a list of integer number, write a predicate to add in list after 
% 1-st, 3-rd, 7-th, 15-th element a given value e.
% b. For a heterogeneous list, formed from integer numbers and list of numbers; 
% add in every sublist after 1-st, 3-rd, 7-th, 15-th 
% element the value found before the sublist in the heterogenous list. 
% The list has the particularity that starts with a number and there aren’t two consecutive elements lists.
% Eg.: [1, [2, 3], 7, [4, 1, 4], 3, 6, [7, 5, 1, 3, 9, 8, 2, 7], 5] =>
% [1, [2, 1, 3], 7, [4, 7, 1, 4, 7], 3, 6, [7, 6, 5, 1, 6, 3, 9, 8, 2, 6, 7], 5]

% a

% Model matematic:
% insert_list(l1...ln, e, pos) =
% 	[], n = 0
% 	l1 + e + insert_pos(l2...ln, e, pos + 1), pos % 2 = 1,
% 	l1 + insert_pos(l2...ln, e, pos + 1), otherwise

% insert_list(L:list, E:number, Pos:number, R:list)
% flow model: insert_list(i, i, i, o)

insert_list([], _, _, []).
insert_list([H|T], E, Pos, [H,E|R]) :- Pos mod 2 =:= 1, !,
    NPos is Pos + 1,
    insert_list(T, E, NPos, R).
insert_list([H|T], E, Pos, [H|R]) :-
    NPos is Pos + 1,
    insert_list(T, E, NPos, R).

% insertNb(L:list, E:number, R:list)
% flow model: insertNb(i, i, o)

insertNb(L, E, R) :- insert_list(L, E, 1, R).

% b

% Model matematic:
% heterList(l1...ln, list) = 
% 	[], n = 0
% 	l1 + insertNb(l2, l1) + heterList(l2...ln), is_list(l2) = True and number(l1)
% 	l1 + heterList(l2...ln), otherwise

% heterList(L:list, R:list)
% flow model: heterList(i, o)

heterList([], []).
heterList([E], [E]).
heterList([H1, H2|T], [H1,HR|R]) :- is_list(H2), number(H1), !,
    insertNb(H2, H1, HR),
    heterList(T, R).
heterList([H|T], [H|R]) :-
    heterList(T, R).